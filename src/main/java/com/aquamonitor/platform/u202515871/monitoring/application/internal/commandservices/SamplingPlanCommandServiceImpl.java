package com.aquamonitor.platform.u202515871.monitoring.application.internal.commandservices;

import com.aquamonitor.platform.u202515871.monitoring.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.u202515871.monitoring.application.commandservices.SamplingPlanCommandService;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.SampleVolume;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.SamplingWindow;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.StationId;
import com.aquamonitor.platform.u202515871.monitoring.domain.repositories.SamplingPlanRepository;
import com.aquamonitor.platform.u202515871.shared.application.result.ApplicationError;
import com.aquamonitor.platform.u202515871.shared.application.result.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SamplingPlanCommandServiceImpl implements SamplingPlanCommandService {
    private static final String SAMPLING_PLAN_RESOURCE = "sampling-plan";

    private final SamplingPlanRepository samplingPlanRepository;

    public SamplingPlanCommandServiceImpl(SamplingPlanRepository samplingPlanRepository) {
        this.samplingPlanRepository = samplingPlanRepository;
    }

    @Override
    public Result<SamplingPlan, ApplicationError> handle(CreateSamplingPlanCommand command) {
        PlanCode planCode = null;
        try {
            planCode = new PlanCode(command.planCode());
            if (samplingPlanRepository.existsByPlanCode(planCode)) {
                return Result.failure(ApplicationError.conflict(SAMPLING_PLAN_RESOURCE, planCode.code()));
            }

            var samplingPlan = new SamplingPlan(
                    planCode,
                    new StationId(command.stationId()),
                    new SamplingWindow(command.samplingWindowStartDate(), command.samplingWindowEndDate()),
                    new SampleVolume(command.sampleVolumeQuantity(), command.sampleVolumeUnit()),
                    command.priorityLevel(),
                    command.status(),
                    command.samplingNotes());
            return Result.success(samplingPlanRepository.save(samplingPlan));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("SamplingPlan", e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            if (planCode != null && samplingPlanRepository.existsByPlanCode(planCode)) {
                return Result.failure(ApplicationError.conflict(SAMPLING_PLAN_RESOURCE, planCode.code()));
            }
            return Result.failure(ApplicationError.unexpected("Sampling plan creation", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("Sampling plan creation", e.getMessage()));
        }
    }
}
